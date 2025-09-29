/**
 * Listener for the poll. Notifies the controller that the poll has been submitted.
 */
public interface SurveyListener {
    /**
     * Called when a user submits survey responses.
     * @param event object with all survey responses
     */
    public void surveySubmitted(SurveyEvent event);
}
