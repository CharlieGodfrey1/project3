import SideBar from "../IncidentSearchComponents/IncidentSideBar";
import NavBar from "../RegSearchComponents/NavBar";
import IncidentResults from "../IncidentSearchComponents/IncidentResults";
import { Col, Row } from "react-bootstrap";
import { useState } from "react";


const IncidentLookUp = () => {

    
    const [Location, setLocation] = useState([]);
    const [selectedArea, setSelectedArea] = useState([]);
    const [searchRadius, setRadius] = useState("");

    const [Loading, setLoading] = useState(false);

    Location.forEach(d => {
        d.timestamp = new Date(d.timestamp);
    });
    
    Location.sort((a, b) => {
        if (a.timestamp < b.timestamp) return -1;
        else if (b.timestamp > a.timestamp) return 1;
        else return 0;
    });



    return (
        <>
            <div id="App">

                <NavBar />


                <Row >

                    <Col sm md lg={3}>

                        <SideBar setSelectedArea={setSelectedArea} setLocation={setLocation} setLoading={setLoading} setRadius={setRadius} searchRadius={searchRadius}/>

                    </Col>

                    <Col sm md lg={9}>


                        <IncidentResults Location={Location} selectedArea={selectedArea} Loading={Loading} searchRadius={searchRadius}/>


                    </Col>
                </Row>

            </div>
        </>
    )
}
export default IncidentLookUp;