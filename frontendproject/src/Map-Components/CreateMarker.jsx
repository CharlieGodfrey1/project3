import { Marker, Tooltip, Popup } from 'react-leaflet';
import BootstrapTable from 'react-bootstrap-table-next';
import L from 'leaflet';



const CreateMarker = ({ Location }) => {

    var filteredLocations = [];
    Location.forEach(location => {
        let inFL = false;
        filteredLocations.forEach(fl => {
            if (fl.latitude == location.latitude && fl.longitude == location.longitude) {
                inFL = true;
            }
        })
        if (!inFL) {
            filteredLocations.push(location);
        }
    })

    const columns = [{

        dataField: 'streetName',
        text: 'Location Seen'
    },
    {
        dataField: 'timestamp',
        text: 'Time Seen'
    }];

    var greenIcon = L.icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    var redIcon = L.icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    return (
        <>
            {filteredLocations.map((location, i, a) =>
                <Marker key={i} position={[location.latitude, location.longitude]} icon={i == 0 ? greenIcon : i == a.length - 1 ? redIcon : new L.Icon.Default}>
                    <Tooltip>
                        {"Street name: " + [location.streetName]}
                        <br></br>
                        {"Capture " + [i + 1]}
                    </Tooltip>
                    <Popup>
                        <BootstrapTable keyField='streetName' data={Location.filter(l => {
                            if (l.latitude == location.latitude && l.longitude == location.longitude) {
                                return true;
                            } else {
                                return false;
                            }
                        }).map(l => {
                            let newL = { ...l, timestamp: l.timestamp.toString() };
                            delete newL.latitude;
                            delete newL.longitude;
                            delete newL.vehicleRegistrationNumber;
                            return newL;
                        })}
                            columns={columns} />
                    </Popup>
                </Marker>
            )}


        </>
    );
}

export default CreateMarker;