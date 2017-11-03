package app.android.com.googlemap01;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bruce Wayne on 01/11/2017.
 */
public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        LatLng posicaoContUniversal = PegaCoordenadaDoEndereco("Rua Welington Moreira César Alves, 281, montes claros");
        if (posicaoContUniversal != null){
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoContUniversal, 17);
        googleMap.moveCamera(update);
        }
    }

    private LatLng PegaCoordenadaDoEndereco(String endereco){

        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco,1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
