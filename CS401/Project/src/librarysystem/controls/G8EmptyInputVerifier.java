package librarysystem.controls;

import javax.swing.JComponent;
import javax.swing.JTextField;

import business.Validator;

public class G8EmptyInputVerifier extends G8InputVerifier {
    String inputTitle;
	public G8EmptyInputVerifier(String inputTitle, boolean allowToProceed) {
		super(allowToProceed);
		this.inputTitle = inputTitle;
	}
	@Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        boolean isValid = !Validator.isEmpty(text);
        return returnTrueIfIgnored("Value ["+ inputTitle +"] cannot be null!!!", isValid, input);                
    }
	
}