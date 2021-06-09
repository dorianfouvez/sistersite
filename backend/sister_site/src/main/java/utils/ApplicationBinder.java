/**
 * @author Fouvez Dorian.
 */
package utils;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import domaine.DomaineFactory;
import domaine.DomaineFactoryImpl;
import domaine.curriculum_vitae.CurriculumVitaeUCC;
import domaine.curriculum_vitae.CurriculumVitaeUCCImpl;
import domaine.photo.PhotoUCC;
import domaine.photo.PhotoUCCImpl;
import domaine.user.UserUCC;
import domaine.user.UserUCCImpl;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import services.CinemaDAO;
import services.CinemaDAOImpl;
import services.CurriculumVitaeDAO;
import services.CurriculumVitaeDAOImpl;
import services.DalBackendServices;
import services.DalServices;
import services.DalServicesImpl;
import services.PhotoDAO;
import services.PhotoDAOImpl;
import services.ShortFilmDAO;
import services.ShortFilmDAOImpl;
import services.StrengthDAO;
import services.StrengthDAOImpl;
import services.TrainingDAO;
import services.TrainingDAOImpl;
import services.UserDAO;
import services.UserDAOImpl;

@Provider
public class ApplicationBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(CinemaDAOImpl.class).to(CinemaDAO.class).in(Singleton.class);
    bind(CurriculumVitaeUCCImpl.class).to(CurriculumVitaeUCC.class).in(Singleton.class);
    bind(CurriculumVitaeDAOImpl.class).to(CurriculumVitaeDAO.class).in(Singleton.class);
    bind(DomaineFactoryImpl.class).to(DomaineFactory.class).in(Singleton.class);
    bind(DalServicesImpl.class).to(DalBackendServices.class).to(DalServices.class)
        .in(Singleton.class);
    bind(PhotoDAOImpl.class).to(PhotoDAO.class).in(Singleton.class);
    bind(PhotoUCCImpl.class).to(PhotoUCC.class).in(Singleton.class);
    bind(ShortFilmDAOImpl.class).to(ShortFilmDAO.class).in(Singleton.class);
    bind(StrengthDAOImpl.class).to(StrengthDAO.class).in(Singleton.class);
    bind(TrainingDAOImpl.class).to(TrainingDAO.class).in(Singleton.class);
    bind(UserDAOImpl.class).to(UserDAO.class).in(Singleton.class);
    bind(UserUCCImpl.class).to(UserUCC.class).in(Singleton.class);

  }

}
