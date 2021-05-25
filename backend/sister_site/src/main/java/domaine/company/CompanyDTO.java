/**
 * @author Fouvez Dorian.
 */
package domaine.company;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CompanyImpl.class)
public interface CompanyDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

}
