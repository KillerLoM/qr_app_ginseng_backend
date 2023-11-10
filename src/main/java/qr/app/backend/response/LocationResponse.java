package qr.app.backend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Location;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private int amount;
    private List<Location> locations;
}
