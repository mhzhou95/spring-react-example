import React, { useState, useEffect} from 'react';

function App() {
  const axios = require('axios');
  const [state, setState] = useState({
    beers: [],
    isLoading: true
  });

  useEffect( ()=> {
    axios.get("http://localhost:8080/")
    .then(response => response.data)  
    .then(data => setState({ ...state, beers:data, isLoading: false}));
  })

  const handleCreate = (event) => {
    event.preventDefault();
    axios.post("http://localhost:8080/", {
      name: event.target.beerName.value
    })
  }

  const handleDelete = (beer) => {
    axios.delete("http://localhost:8080/", {
      data: beer
    })
  }
  const { beers, isLoading } = state;
  if( isLoading ) {
    return <p> Is loading... </p>
  }
    return(
      <div>
        <h1>Beer List</h1>
        {beers.map( beer => 
          <div key={beer.id}>
            <p>{beer.name} 
              <button onClick={ ()=> {handleDelete(beer)}}>X</button>
            </p>
          </div>
        )}

        <form onSubmit={handleCreate}>
            <label>BeerName </label><input type="text" name="beerName"></input>
            <button>Add Beer</button>
        </form>
      </div>  
    )
}

export default App;
