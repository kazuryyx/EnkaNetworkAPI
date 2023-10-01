package me.kazury.enkanetworkapi.starrail.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.exceptions.UpdateLibraryException;

/**
 * TODO - Add documentation
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRCharacterData {
    public SRCharacterData() {
        throw new UpdateLibraryException();
    }
}
