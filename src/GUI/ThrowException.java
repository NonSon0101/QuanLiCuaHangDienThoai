/**class này sẽ thực thi interface InputCheck và thêm vào checkInput các logic cần thiết*/
package GUI;

import Exception.EmptyInputException;

public class ThrowException implements InputCheck {
    @Override
    public void checkInput(String input) throws EmptyInputException {
        if (input.trim().isEmpty()) {
            throw new EmptyInputException();
        }
    }
}
