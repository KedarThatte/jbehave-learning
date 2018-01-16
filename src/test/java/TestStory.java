import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.mvnjbehaveselinium.steps.TestStorySteps;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.reporters.StoryReporterBuilder.Format.*;

/**
 * Created by Kedar on 14-01-2018.
 */
public class TestStory extends JUnitStories {

    public TestStory(){
        configuredEmbedder().embedderControls()
                .doIgnoreFailureInStories(true)
                .useStoryTimeoutInSecs(10000);
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        ParameterConverters parameterConverters = new ParameterConverters();
        parameterConverters.addConverters(
                new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd"))
        );
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser())
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withDefaultFormats()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withFormats(StoryReporterBuilder.Format.HTML, StoryReporterBuilder.Format.CONSOLE, StoryReporterBuilder.Format.STATS, StoryReporterBuilder.Format.TXT, StoryReporterBuilder.Format.XML)
                                .withFailureTrace(false)
                                .withRelativeDirectory("jbehave")



                );

    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        //ArrayList<Steps> stepFileList = new ArrayList<Steps>();
        //stepFileList.addAll(new GoogleSteps());

        return new InstanceStepsFactory(configuration(), new TestStorySteps());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
                        Arrays.asList("**/*.story"),
                        Arrays.asList(""));
    }
    @Test
    public void  scenarios() throws Throwable {
        TestStory scenarios = new TestStory();
        try {
            scenarios.run();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


}
