package qr.app.backend.response;

import lombok.*;
import qr.app.backend.model.Location;
import qr.app.backend.model.Newspapers;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewspapersResponse {
    private int amount;
    private List<Newspapers> newspapers;
}
