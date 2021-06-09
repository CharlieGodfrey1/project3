import { MapContainer, TileLayer, Circle } from 'react-leaflet';
import CreateMarker from './CreateMarker';
import Line from './Polyline'





const MapComponent = ({ Location, DefaultZoom }) => {
    return (
        <>
            <div className="leaflet-container">
                {Location[0] && <MapContainer center={[Location[0].latitude, Location[0].longitude]} zoom={11} >

                    <TileLayer
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors' />
                    <CreateMarker Location={Location} />
                    
                    <Line Location={Location} />
                </MapContainer>
                }
            </div>
        </>
    )

}



export default MapComponent;