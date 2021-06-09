/**
 * @author Fouvez Dorian.
 */
package domaine.cinema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ComplexCinemaImpl.class)
public interface ComplexCinemaDTO {

}
