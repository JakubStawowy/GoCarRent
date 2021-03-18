import './App.css';

import LeftSidebar from "./components/LeftSidebar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./components/Home";
import RightSidebar from "./components/RightSidebar";
import React from "react";


function App() {
  return (
    <Router>
        <div className={"container"}>
            <LeftSidebar />
            <RightSidebar />
            <Switch>
                <Route path={'/home'} component={Home}/>
            </Switch>
        </div>
    </Router>
  );
}

export default App;
