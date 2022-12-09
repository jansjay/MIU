package librarysystem.controls;

import javax.swing.JComponent;
import javax.swing.JTextField;

import business.Validator;

public class G8NumberWithLengthInputVerifier extends G8NumberInputVerifier {
	int integerLength = 0;
    public G8NumberWithLengthInputVerifier(String inputTitle, int integerLength, boolean allowToProceed) {
		super(inputTitle, allowToProceed);
		this.integerLength = integerLength;
	}
	@Override
    public boolean verify(JComponent input) {
		if(!super.verify(input))
        	return false;
        String text = ((JTextField) input).getText();
        boolean isValid = Validator.isValidIntegerWithLength(text, integerLength);        
        return returnTrueIfIgnored("Value ["+ inputTitle +"] should be a valid Number with length ["+ integerLength + "]!!!", isValid, input);                
    }	
}