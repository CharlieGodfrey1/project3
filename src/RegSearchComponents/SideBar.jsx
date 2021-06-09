import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import Search from './Search';




const SideBar = (props) => {

    


    const [allData, setAllData] = useState([]);
    const [filteredData, setFilteredData] = useState(allData);



    return (
        <>

            <div className="sidebar " >
                <div sm md lg={3} >
                    <div id="sidebarTitle">
                        <h2>VEHICLE LOOKUP</h2>
                    </div>


                    <Form >
                        <Form.Group controlId="formCarReg" >

                            <Search setLocation={props.setLocation} setSelectedSuspect={props.setSelectedSuspect} setLoading={props.setLoading} />
                        </Form.Group>
                    </Form>

                </div>
            </div>

        </>
    )

};

export default SideBar;