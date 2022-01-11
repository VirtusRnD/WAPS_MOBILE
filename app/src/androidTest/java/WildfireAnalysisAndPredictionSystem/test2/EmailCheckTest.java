package WildfireAnalysisAndPredictionSystem.test2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailCheckTest {

    @Test
    public void isValidEmail(){
        assertTrue(SignUpPageActivity.isValidEmail("hasanali@posta.com"));

    }
}