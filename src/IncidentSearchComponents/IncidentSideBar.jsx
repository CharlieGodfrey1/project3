import Form from 'react-bootstrap/Form';
import IncidentSearch from './IncidentSearch'
import { useState} from 'react';


const IncidentSideBar = (props, searchRadius) => {

    

    return (
        <>

            <div className="sidebar">
                <div id="sidebarTitle">
                    <h2>INCIDENT SEARCH</h2>
            </div>
                <Form >
                    <Form.Group controlId="formCarReg" >
                        <IncidentSearch setLocation={props.setLocation} setSelectedArea={props.setSelectedArea} setLoading={props.setLoading} setRadius={props.setRadius} searchRadius={props.searchRadius}/>
                    </Form.Group>
                </Form>
            </div>

        </>
    )

};

export default IncidentSideBar;