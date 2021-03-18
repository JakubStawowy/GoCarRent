import './App.css';

import LeftSidebar from "./components/LeftSidebar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./views/Home";
import RightSidebar from "./components/RightSidebar";
import React from "react";
import {Grid} from "@material-ui/core";
import Add from "./views/Add";
import RentedCars from "./views/RentedCars";
import Profile from "./views/Profile";
import UserCars from "./views/UserCars";
import Settings from "./views/Settings";
import Messages from "./views/Messages";
import {Redirect} from "react-router";

function App() {
  return (
    <Router>
        <div className={"container"}>
            <Switch>
                <Grid container style={{height: '100vh'}}>
                    <LeftSidebar />
                        <Route exact path={'/'}>
                            <Redirect to={'/home'} />
                        </Route>
                        <Route path={'/home'} component={Home}/>
                        <Route path={'/add'} component={Add}/>
                        <Route path={'/cars/rented'} component={RentedCars}/>
                        <Route path={'/logout'} component={Home}/>
                        <Route path={'/user/profile'} component={Profile}/>
                        <Route path={'/messages'} component={Messages}/>
                        <Route path={'/user/cars'} component={UserCars}/>
                        <Route path={'/settings'} component={Settings}/>
                    <RightSidebar />
                </Grid>
            </Switch>
        </div>
    </Router>
  );
}

export default App;
