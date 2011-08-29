package com.jphilli85.example.map;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapPage extends MapActivity {
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyItemizedOverlay itemizedOverlay;
	GeoPoint current;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        mapOverlays = mapView.getOverlays();
        drawable = getResources().getDrawable(R.drawable.recellular_marker);
        itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
        
        GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Mexico City", "Hola, Mundo!");
	    
	    GeoPoint point2 = new GeoPoint(35410000, 139460000);
	    OverlayItem overlayitem2 = new OverlayItem(point2, "Tokyo", "Sekai, konichiwa!");
	    
	    int lat = (int) (getIntent().getExtras().getDouble("lat") * 1E6);
	    int lon = (int) (getIntent().getExtras().getDouble("lon") * 1e6);
	    current = new GeoPoint(lat, lon);
	    OverlayItem overlayitem3 = new OverlayItem(current, "You", "Hello!");
	    
	    itemizedOverlay.addOverlay(overlayitem);
	    itemizedOverlay.addOverlay(overlayitem2);
	    itemizedOverlay.addOverlay(overlayitem3);
	    mapOverlays.add(itemizedOverlay);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
