import { MapContainer, TileLayer, Circle } from 'react-leaflet';
import IncidentCreateMarker from './IncidentCreateMarker';







const IncidentMap = ({ Location, searchRadius }) => {

    return (
        <>
            <div>
                {Location[0] && <MapContainer center={[Location[0].latitude, Location[0].longitude]} zoom={12} >
                    <TileLayer
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors' />
                    <IncidentCreateMarker Location={Location} />
                    <Circle center={[Location[0].latitude, Location[0].longitude]} radius={searchRadius * 1000}  />
                </MapContainer>
                }
            </div>
        </>
    )

}


export default IncidentMap;