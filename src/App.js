import './App.css';
import RegLookUp from './Components/Reg-Look-Up.jsx';
import { Route, BrowserRouter, Switch } from 'react-router-dom';
import RedirectRoute from './Login/RedirectRoute';
import NotFound from './Components/NotFound';
import incidentlookup from './Components/incident-look-up'


import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path='/' component={RegLookUp} />        
        <RedirectRoute path="/reg-search" component={RegLookUp} />
        <RedirectRoute path="/incident-search" component={incidentlookup} />
        <Route component={NotFound} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
