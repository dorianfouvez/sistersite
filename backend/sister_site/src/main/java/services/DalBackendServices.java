/**
 * @author Biçakçioglu Michaël.
 */
package services;

import java.sql.PreparedStatement;

public interface DalBackendServices {

  PreparedStatement getPreparedStatement(String query);

}
