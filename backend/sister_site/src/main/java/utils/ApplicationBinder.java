/**
 * @author Fouvez Dorian.
 */
package utils;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import domaine.DomaineFactory;
import domaine.DomaineFactoryImpl;
import domaine.photo.PhotoUCC;
import domaine.photo.PhotoUCCImpl;
import domaine.user.UserUCC;
import domaine.user.UserUCCImpl;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import services.DalBackendServices;
import services.DalServices;
import services.DalServicesImpl;
import services.PhotoDAO;
import services.PhotoDAOImpl;
import services.UserDAO;
import services.UserDAOImpl;

@Provider
public class ApplicationBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(DomaineFactoryImpl.class).to(DomaineFactory.class).in(Singleton.class);
    bind(DalServicesImpl.class).to(DalBackendServices.class).to(DalServices.class)
        .in(Singleton.class);
    bind(PhotoDAOImpl.class).to(PhotoDAO.class).in(Singleton.class);
    bind(PhotoUCCImpl.class).to(PhotoUCC.class).in(Singleton.class);
    bind(UserDAOImpl.class).to(UserDAO.class).in(Singleton.class);
    bind(UserUCCImpl.class).to(UserUCC.class).in(Singleton.class);

  }

}
