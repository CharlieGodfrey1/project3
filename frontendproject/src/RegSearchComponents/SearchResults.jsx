import { Tabs, Tab } from "react-bootstrap";
import BootstrapTable from 'react-bootstrap-table-next';
import Loader from '../Map-Components/Loader'
import Map from '../Map-Components/Map'


const SearchResults = ({ selectedSuspect, Location, Loading, DefaultZoom }) => {




    const results = selectedSuspect.vehicles.map(({ vehicleRegistrationNumber, make, model }, i) =>

        <span key={i}>
            <h2 id="searchResultsTitle">{vehicleRegistrationNumber}</h2>
            <p><strong>Make: </strong>{make}</p>
            <p><strong>Model: </strong>{model}</p>
            <p><strong>Owner: </strong>{selectedSuspect.forenames} {selectedSuspect.surname}</p>
            <p><strong>Driver License: </strong> {selectedSuspect.driverLicenceId}</p>
        </span>
    )
    const locationData = Location.map(Location => ({

        streetName: Location.streetName,
        vehicleRegistrationNumber: Location.vehicleRegistrationNumber,
        timestamp: Location.timestamp.toString()

    }));
    

    const columns = [{
        dataField: 'vehicleRegistrationNumber',
        text: 'Registration Plate'
    }, {
        dataField: 'streetName',
        text: 'Location Seen'
    },
    {
        dataField: 'timestamp',
        text: 'Time Seen'
    }];



    return (

        <>
            <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example" className="tabs">
                <Tab eventKey="" title="Vehicle Details">
                    {results}
                </Tab>
                <Tab eventKey="table" title="Tracking Details">

                    <BootstrapTable keyField='id' data={locationData} columns={columns} />
                </Tab>
            </Tabs>

            <div className="mapContainer">
                {Loading ? 
                <Loader /> : (
                <Map Location={Location} DefaultZoom={DefaultZoom} /> 
                
                )}

            </div>


        </>
    )
}
export default SearchResults;