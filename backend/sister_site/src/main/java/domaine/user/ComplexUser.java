package domaine.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ComplexUserImpl.class)
public interface ComplexUser extends ComplexUserDTO {

  boolean checkPassword(String password);

  String hashPassword(String password);

}
