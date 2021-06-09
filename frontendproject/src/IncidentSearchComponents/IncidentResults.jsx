import IncidentMap from '../Map-Components/IncidentMap'
import Loader from '../Map-Components/Loader';
import BootstrapTable from 'react-bootstrap-table-next';


const SearchResults = ({ selectedArea, Location, Loading, searchRadius }) => {


  const areaData = selectedArea.map(area => ({
    streetName: area.streetName,
    vehicleRegNo: area.vehicleRegNo,
    timestamp: area.timestamp.toString(),
    surname: area.surname,
    firstname: area.forename
  }));


  const columns = [{
    dataField: 'streetName',
    text: 'Location Seen'
  },
  {
    dataField: 'timestamp',
    text: 'Time Seen'
  },
  {
    dataField: 'vehicleRegNo',
    text: 'Registration Plate'
  },
  {
    dataField: 'firstname',
    text: 'Forename'
  },
  {
    dataField: 'surname',
    text: ' Surname'
  }
  ];



  return (

    <>


      <div className="mapContainer">

      {Loading ? 
                <Loader /> : (
        <IncidentMap Location={Location} searchRadius={searchRadius} />
                )}
        </div>
    
    <div className='areaTable'>
        <BootstrapTable keyField='id' data={areaData} columns={columns}  />
    </div>

    </>
  )
}
export default SearchResults;