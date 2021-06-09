/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.strength.StrengthWithOrderDTO;

public interface StrengthDAO {

  List<StrengthWithOrderDTO> getAllStrengthWithOrderForCV(int cvId);



  // ******************** Static's Methods ********************

  static String getAllStrengthAttributes() {
    return " st.id, st.label";
  }

  static String getStrengthTableName() {
    return " ambre_fouvez.strengths st";
  }

}
