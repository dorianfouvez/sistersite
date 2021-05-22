/**
 * @author Fouvez Dorian.
 */
package domaine.role;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = RoleImpl.class)
public interface RoleDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

}
