package com.googlecode.yatspec.rendering.html;

import com.googlecode.yatspec.rendering.ContentFromFile;
import com.googlecode.yatspec.rendering.Renderer;
import com.googlecode.yatspec.state.Scenario;
import com.googlecode.yatspec.state.TestResult;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

public class HtmlResultRendererTest {

    public static final String CUSTOM_RENDERED_TEXT = "some crazy and likely random string that wouldn't appear in the html";

    @Test
    public void providesLinksToResultOutputRelativeToOutputDirectory() throws Exception {
        assertThat(
                HtmlResultRenderer.htmlResultRelativePath(this.getClass()),
                is(Paths.get("com/googlecode/yatspec/rendering/html/HtmlResultRendererTest.html").toString()));
    }

    @Test
    public void loadsTemplateOffClassPath() throws Exception {
        TestResult result = new TestResult(this.getClass());

        String html = new HtmlResultRenderer().render(result);

        assertThat(html, is(not(nullValue())));
    }

    @Test
    public void supportsCustomRenderingOfScenarioLogs() throws Exception {
        TestResult result = aTestResultWithCustomRenderTypeAddedToScenarioLogs();

        String html = new HtmlResultRenderer().
                withCustomRenderer(RenderedType.class, new DefaultReturningRenderer(CUSTOM_RENDERED_TEXT)).
                render(result);

        assertThat(html, containsString(CUSTOM_RENDERED_TEXT));
    }

    @Test
    public void supportsCustomHeaderContent() throws Exception {
        TestResult result = new TestResult(getClass());
        ContentFromFile content = new ContentFromFile("rendering/html/CustomHeaderContent.html");

        String html = new HtmlResultRenderer().
                withCustomHeaderContent(content).
                render(result);

        assertThat(html, containsString(content.toString()));
    }

    /**
     * with the following line uncommented out group.registerRenderer(always().and(not(instanceOf(Number.class))), Xml.escape());
     *
     * we get &quot;blah&quot; instead of "blah"
     * assertThat(html, containsString("var something = &quot;blah&quot;;"));
     */
    @Test
    public void supportsCustomJavaScript() throws Exception {
        TestResult result = new TestResult(getClass());
        ContentFromFile content = new ContentFromFile("rendering/html/customJavaScript.js");

        String html = new HtmlResultRenderer().
                withCustomScripts(content).
                render(result);

        assertThat(html, containsString(content.toString()));
    }

    private TestResult aTestResultWithCustomRenderTypeAddedToScenarioLogs() throws Exception {
        TestResult result = new TestResult(getClass());
        addToCapturedInputsAndOutputs(result, new RenderedType());
        return result;
    }

    private void addToCapturedInputsAndOutputs(TestResult result, Object thingToBeCustomRendered) throws Exception {
        Scenario scenario = result.getTestMethods().get(0).getScenarios().get(0);
        TestState testState = new TestState();
        testState.capturedInputAndOutputs.add("custom rendered thing", thingToBeCustomRendered);
        scenario.setTestState(testState);
    }

    public static class RenderedType {
    }

    private class DefaultReturningRenderer implements Renderer<RenderedType> {
        private String rendererOutput;

        public DefaultReturningRenderer(final String rendererOutput) {
            this.rendererOutput = rendererOutput;
        }

        public String render(RenderedType renderedType) throws Exception {
            return rendererOutput;
        }
    }
}
