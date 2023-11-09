package qr.app.backend.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import qr.app.backend.model.Ginseng;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GinsengResponse {
    private int amount;
    private List<Ginseng> ginsengs;

}
