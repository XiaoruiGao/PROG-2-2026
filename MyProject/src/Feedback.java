public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;
    public Feedback(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5){
    if(isConcatenation){
        completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }else{
        completeFeedback = feedbackUsingstringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
    }
        checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }
    private void createReviewID(String firstName, String lastName, String feedback){
        reviewID = ((firstName + lastName).substring(2,6).toUpperCase() + completeFeedback.substring(10,15).toLowerCase()+completeFeedback.length()+"_"+System.currentTimeMillis()).replace(" ", "");
    }
    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5){
        String concatentedFeedback = " ";
        concatentedFeedback += sent1;
        concatentedFeedback += sent2;
        concatentedFeedback += sent3;
        concatentedFeedback += sent4;
        concatentedFeedback += sent5;
        return concatentedFeedback;
    }
private StringBuilder feedbackUsingstringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5){
    StringBuilder sb = new StringBuilder();
    sb.append(sent1);
    sb.append(sent2);
    sb.append(sent3);
    sb.append(sent4);
    sb.append(sent5);
    return sb;
   }
   private boolean checkFeedbackLength(String feedback){
    if(feedback.length() > 500){
        longFeedback = true;
        return longFeedback;
    }
    longFeedback = false;
    return longFeedback;
   }
   @Override
   public String toString(){
    return "Feedback from" + firstName + " " + lastName + " (" + email +"):\n\n" +
    "Feedback: " + completeFeedback + "\n\n" +
    "Long Feedback: " + longFeedback + "\n" +
    "Review ID: " + reviewID;

   }
   public static void main(String[] args) throws Exception{
    Feedback feedback = new Feedback("John", "Doe", "1234@qq");
    String sent1 = "I was very satisfied with the service.";
    String sent2 = "The e-Bike is quite comfortable to ride";
    String sent3 = "The battery life of the e-Bike is impressive";
    String sent4 = "The customer support was helpful and responsive";
    String sent5 = "I would recommend this e-Bike to my friends and family.";
    feedback.analyseFeedback( true, sent1, sent2, sent3, sent4, sent5);
    System.out.println(feedback.toString());

   }

}
