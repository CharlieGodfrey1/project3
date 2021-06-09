import axios from 'axios';
import { useEffect, useState } from 'react';
import { Polyline } from 'react-leaflet';




const Line = ( {Location} ) => {




    const PolylineTest = [

        Location.map(location => [location.latitude, location.longitude])
    ]


    return (
        <>
            <Polyline positions={PolylineTest}></Polyline>)
        </>
    );
}

export default Line;