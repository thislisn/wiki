package com.wiki.example.framework;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import io.cucumber.core.gherkin.Scenario;
import lombok.Getter;
import org.joda.time.DateTime;

import java.util.List;

@Getter
public class TestRailHelper {
    private static final String APPLICATION_NAME = "wiki test rail";
    private static final String END_POINT = "https://some.testrail.net/";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PROJECT_NAME = "Wiki Project";
    private static final String SUITE_NAME = "Automation wiki suite";
    private static final String SECTION_NAME = "wiki section";
    private static final String TEST_CASE_TITLE = "Check links on search result page";
    private static final String AUTOMATED_CUSTOM_FIELD = "automation";
    private static final String STATUS_CUSTOM_FIELD = "status";
    private static final int AUTOMATED = 3;
    private static final int COMPLETED_STATUS = 4;
    private TestRail testRail;
    private Project project;
    private Suite suite;
    private Section section;
    private List<CaseField> caseFieldsList;
    private Case testCase;
    private Run run;
    private List<ResultField> resultFields;
    private List<CaseField> caseFields;

    public void run() {
        if (getTestRail() == null) {
            this.testRail = TestRail.builder(END_POINT, USERNAME, PASSWORD).applicationName(APPLICATION_NAME).build();
            this.project = testRail.projects().add(new Project().setName(PROJECT_NAME)).execute();
            this.suite = testRail.suites().add(project.getId(), new Suite().setName(SUITE_NAME)).execute();
            this.caseFieldsList = testRail.caseFields().list().execute();
            this.section = testRail.sections().add(project.getId(), new Section().setName(SECTION_NAME)).execute();
            this.testCase = testRail.cases().add(section.getId(), new Case().setTitle(TEST_CASE_TITLE), caseFieldsList).execute();
            this.run = testRail.runs().add(project.getId(), new Run().setSuiteId(suite.getId()).setName(String.format("night regression: %s", DateTime.now().toString()))).execute();
        }
    }

    public void setTestCaseExecutionResult(Case testCase, Scenario scenario) {
        int testRailRunId = run.getId();
        int statusId = 1;
        testRail.tests().list(testRailRunId).execute().stream()
                .filter(test -> test.getCaseId() == testCase.getId())
                .findFirst()
                .ifPresent(test -> testRail.results().addForCase(testRailRunId, testCase.getId(),
                        new Result().setStatusId(statusId), resultFields).execute());
    }

    public void markCaseAsAutomated(Case testCase){
        testCase.getCustomFields().put(AUTOMATED_CUSTOM_FIELD, AUTOMATED);
        testCase.getCustomFields().put(STATUS_CUSTOM_FIELD, COMPLETED_STATUS);
        testRail.cases().update(testCase, caseFields).execute();
    }

    public void closeRun() {
        testRail.runs().close(run.getId()).execute();
    }

    public void setProjectStatusCompleted(){
        testRail.projects().update(project.setCompleted(true)).execute();
    }
}
