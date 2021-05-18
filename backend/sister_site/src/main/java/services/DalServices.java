/**
 * @author Biçakçioglu Michaël.
 */
package services;

public interface DalServices {

  void startTransaction();

  void commitTransaction();

  void rollbackTransaction();

}
