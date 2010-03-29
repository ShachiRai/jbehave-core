package org.jbehave.examples.trader.stories;

import static org.jbehave.core.reporters.StoryReporterBuilder.Format.CONSOLE;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.HTML;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.TXT;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.XML;

import org.jbehave.core.Configuration;
import org.jbehave.core.JUnitStory;
import org.jbehave.core.parser.*;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.examples.trader.PriorityMatchingSteps;
import org.jbehave.core.MostUsefulConfiguration;
import org.jbehave.core.parser.PatternStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.StepsConfiguration;
import org.jbehave.core.steps.StepsFactory;

public class PriorityMatching extends JUnitStory {

    private static StoryNameResolver resolver = new UnderscoredCamelCaseResolver(".story");

    public PriorityMatching() {
         Configuration storyConfiguration = new MostUsefulConfiguration() {
            @Override
            public StoryDefiner forDefiningStories() {
                return new ClasspathStoryDefiner(resolver, new PatternStoryParser(keywords()));
            }

            @Override
            public StoryReporter forReportingStories() {
                return new StoryReporterBuilder(new FilePrintStreamFactory(PriorityMatching.class, resolver))
                        .with(CONSOLE)
                        .with(TXT)
                        .with(HTML)
                        .with(XML)
                        .build();
            }

        };
        useConfiguration(storyConfiguration);

        StepsConfiguration stepsConfiguration = new StepsConfiguration();
        stepsConfiguration.usePatternBuilder(new PrefixCapturingPatternBuilder("$"));
        addSteps(new StepsFactory(stepsConfiguration).createCandidateSteps(new PriorityMatchingSteps()));

    }

}
