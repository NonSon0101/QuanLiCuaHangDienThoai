/** Interface này định nghĩa checkInput ném ra một EmptyInputException*/
package GUI;

import Exception.EmptyInputException;
public interface InputCheck {
    void checkInput(String input) throws EmptyInputException;
}
