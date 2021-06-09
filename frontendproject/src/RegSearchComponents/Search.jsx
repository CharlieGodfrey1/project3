import { useState } from 'react';
import axios from 'axios';
import React from 'react';
import { Button } from "react-bootstrap";



const Search = (props) => {


  const [searchReg, setReg] = useState("");




  const searchResult = () => {

    props.setLoading(true)
    axios.get('/api/getSuspectInfo/' + searchReg)
      .then(response => {
        props.setSelectedSuspect(response.data.suspect);
        props.setLocation(response.data.obs);
        props.setLoading(false)

      })
      .catch(err => {
        if (err.response.status === 500) {
          throw new Error(`Number plate not found`);
        }
        throw err;
      });


  }

  return (
    <>
      <label id="searchLabel">Search Registration:</label>
      <input type="text" placeholder="ENTER REGISTRATION" defaultValue={setReg} onChange={event => setReg(event.target.value)} className="inputSearchBar" />
      <br></br>
      <Button variant="primary" onClick={e => searchResult(e.preventDefault())} id="searchButton" type="submit" >
        SEARCH
      </Button>
    </>
  )
}

export default Search;