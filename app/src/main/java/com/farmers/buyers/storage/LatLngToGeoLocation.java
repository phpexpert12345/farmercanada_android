package com.farmers.buyers.storage;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LatLngToGeoLocation {

    public LatLngToGeoLocation() {
    }

    /**
     * Get list of address by latitude and longitude
     *
     * @return null or List<Address>
     */
    public List<Address> getGeocoderAddress(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            return addresses;
        } catch (IOException e) {
            //e.printStackTrace();
            //Log.e("Error : Geocoder", "Impossible to connect to Geocoder", e);
        }
        return null;
    }

    /**
     * Try to get AddressLine
     *
     * @return null or addressLine
     */
    public String getAddressLine(Context context, double latitude, double longitude) {
        List<Address> addresses = getGeocoderAddress(context, latitude, longitude);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String addressLine = address.getAddressLine(0);
           // System.out.println("Address line" + address.toString() + "  " + address);
            return addressLine;
        } else {
            return null;
        }
    }

    /**
     * Try to get Locality
     *
     * @return null or locality
     */
    public String getLocality(Context context, double latitude, double longitude) {
        List<Address> addresses = getGeocoderAddress(context, latitude, longitude);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String locality = address.getLocality();

            return locality;
        } else {
            return null;
        }
    }

    /**
     * Try to get Postal Code
     *
     * @return null or postalCode
     */
    public String getPostalCode(Context context, double latitude, double longitude) {
        List<Address> addresses = getGeocoderAddress(context, latitude, longitude);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String postalCode = address.getPostalCode();

            return postalCode;
        } else {
            return null;
        }
    }

    /**
     * Try to get CountryName
     *
     * @return null or postalCode
     */
    public String getCountryName(Context context, double latitude, double longitude) {
        List<Address> addresses = getGeocoderAddress(context, latitude, longitude);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String countryName = address.getCountryName();
            //   country.setText(countryName);
            return countryName;
        } else {
            return null;
        }
    }

    /**
     * Try to get AdminArea
     *
     * @return null or AdminArea
     */
    public String getAdminArea(Context context, double latitude, double longitude) {
        List<Address> addresses = getGeocoderAddress(context, latitude, longitude);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String adminArea = address.getAdminArea();
            //   country.setText(countryName);
            return adminArea;
        } else {
            return null;
        }
    }
}