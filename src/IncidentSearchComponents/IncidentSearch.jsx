import { useState } from "react";
import axios from "axios";
import React from "react";
import { Button } from "react-bootstrap";

const IncidentSearch = (props) => {

  const [searchArea, setArea] = useState("");
  const [searchInterval, setInterval] = useState("");
  const [searchRadius, setRadius] = useState("");
  const [searchDate, setDate] = useState("");
  const [searchTime, setTime] = useState("");

  const searchResultArea = () => {
    props.setLoading(true);
    axios
      .get(
        "api/findAllInArea/" +
          searchDate +
          " " +
          searchTime +
          "/" +
          searchArea +
          "/" +
          searchInterval +
          "/" +
          props.searchRadius
      )
      .then((response) => {
        console.log("AREA ", response.data);
        props.setSelectedArea(response.data.vehicle);
        props.setLocation(response.data.vehicle);
        props.setTransaction(response.data.transaction);
        props.setLoading(false);
      })
      .catch((error) => console.log(error));
  };

  return (
    <>
      {/* // Search Bar */}

      <label id="searchLabel">Search Incident Location:</label>
      <input
        type="text"
        placeholder="Enter Location"
        onChange={(event) => setArea(event.target.value)}
        className="inputSearchBar"
      />
      <br></br>
      <label id="searchLabel">Search Incident Date:</label>
      <input
        type="date"
        placeholder="Enter Date"
        onChange={(event) => setDate(event.target.value)}
        className="inputSearchBar"
      />
      <br></br>
      <label id="searchLabel">Search Incident Time:</label>
      <input
        type="time"
        step="1"
        onChange={(event) => setTime(event.target.value)}
        className="inputSearchBar"
      />
      <br></br>
      <label id="searchLabel">Search Incident Time Interval:</label>
      <input
        type="text"
        placeholder="Enter Time Interval in minutes"
        onChange={(event) => setInterval(event.target.value)}
        className="inputSearchBar"
      />
      <br></br>
      <label id="searchLabel">Search Incident Radius:</label>
      <input
        type="text"
        placeholder="Enter Radius in KM"
        onChange={(event) => props.setRadius(event.target.value)}
        className="inputSearchBar"
      />
      <br></br>
      <Button
        variant="primary"
        onClick={(e) => searchResultArea(e.preventDefault())}
        id="searchButton"
        type="submit"
      >
        SEARCH

      </Button>
    </>
  );
};

export default IncidentSearch;

