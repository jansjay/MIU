package librarysystem.controls;

import java.awt.Container;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public abstract class G8InputVerifier extends InputVerifier {
    protected boolean allowToProceed;
	protected G8InputVerifier(boolean allowToProceed) {
    	this.allowToProceed = allowToProceed;
    }
	protected void reportError(String text, JComponent input) {
		getG8JFrame(input).setErrorMessage(text);
	}
	protected void reportWarning(String text, JComponent input) {
		getG8JFrame(input).setWarningMessage(text);
	}
	protected boolean returnTrueIfIgnored(String message, boolean isValid, JComponent input) {
		if(!isValid && this.allowToProceed) {
			reportWarning(message, input);
			return true;
		}
		else if(!isValid) {
			reportError(message, input);
		}
		else {
			//Clear
			reportWarning("", input);
		}
		return isValid;
	}
	protected G8JFrame getG8JFrame(JComponent input) {
		Container container = input.getParent();
		while(!(container instanceof G8JFrame) && container != null) {
			container = container.getParent();
		}
		return (G8JFrame)container;
	}
}