/**
 * @author Fouvez Dorian.
 */
package services;

public interface ProfessionDAO {

  // ******************** Static's Methods ********************

  static String getAllProfessionAttributes() {
    return " pr.id, pr.label";
  }

  static String getProfessionTableName() {
    return " ambre_fouvez.professions pr";
  }

}
