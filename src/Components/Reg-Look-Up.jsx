import SideBar from "../RegSearchComponents/SideBar";
import NavBar from "../RegSearchComponents/NavBar";
import SearchResults from "../RegSearchComponents/SearchResults";
import { Col, Row } from "react-bootstrap";
import { useState } from "react";


const RegLookUp = () => {

    const [selectedSuspect, setSelectedSuspect] = useState({ vehicles: [] });
    const [Location, setLocation] = useState([]);

    const [DefaultZoom, setdefaultZoom] = useState(6)
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
                        <SideBar setSelectedSuspect={setSelectedSuspect} setLocation={setLocation} setdefaultZoom={setdefaultZoom} setLoading={setLoading} />
                    </Col>

                    <Col sm md lg={9}>

                        <SearchResults selectedSuspect={selectedSuspect} Location={Location} DefaultZoom={DefaultZoom} Loading={Loading} />

                    </Col>
                </Row>

            </div>
        </>
    )
}
export default RegLookUp;