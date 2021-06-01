import './App.css';

import LeftSidebar from "./components/LeftSidebar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./views/Home";
import RightSidebar from "./components/RightSidebar";
import FilteringPanel from "./components/FilteringPanel";
import React from "react";
import {Grid, List, makeStyles} from "@material-ui/core";
import AddAnnouncement from "./views/AddAnnouncement";
import EditAnnouncement from "./views/EditAnnouncement";
import UserCars from "./views/UserCars";
import Profile from "./views/Profile";
import Settings from "./views/Settings";
import LoginRegister from "./views/LoginRegister";
import Messages from "./views/Messages";
import {Redirect} from "react-router";
import logo from "./uploads/background-logo.png";
import RentedCars from "./views/RentedCars";
import {useSelector} from "react-redux";

const useStyles = makeStyles((theme) => ({
    main: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        maxHeight: '100vh',
        overflow: 'auto'
    },
    footer: {
        background: '#6A6464C2 0% 0% no-repeat padding-box',
        opacity: 1,
        display: 'flex',
        justifyContent: 'end',
        borderRadius: 0,
        '&:hover': {
            background: '#8C8686E4 0% 0% no-repeat padding-box'
        },
        flex: '1'
    },
    subContainer: {
        flex: '16'
    },
    logo: {
        position: 'fixed',
        height: '90%',
        left: '50%',
        marginLeft: '-25%',
        opacity: 0.3,
        zIndex: -1
    }
}));
function App() {

    const classes = useStyles();
    const loggedSelector = useSelector((state) => state.isLogged);

    return (
        <Router>
            <img src={logo} alt={''} className={classes.logo}/>
            <div className={"container"}>
                <Switch>
                    <Grid container style={{height: '100vh'}} wrap={"nowrap"}>
                        <Grid item component={LeftSidebar}/>
                        <Grid container xs={8} component={List} className={classes.main} wrap={'nowrap'}>
                            <Grid item className={classes.subContainer}>
                                <Route exact path={'/'}>
                                    {loggedSelector.logged ? <Redirect to={'/home'}/> : <Redirect to={'/login'}/>}
                                </Route>
                                <Route path={'/home'} component={Home}/>
                                <Route path={'/announcement/:id/edit'} component={EditAnnouncement}/>
                                <Route path={'/announcement/filter'} component={FilteringPanel}/>
                                <Route path={'/add'} component={AddAnnouncement}/>
                                <Route path={'/user/{id}/rented'} component={RentedCars}/>
                                <Route path={'/login'} component={LoginRegister}/>
                                <Route path={'/users/:id/profile'} component={Profile}/>
                                <Route path={'/users/{id}/messages'} component={Messages}/>
                                <Route path={'/users/{id}/cars'} component={UserCars}/>
                                <Route path={'/settings'} component={Settings}/>
                            </Grid>
                        </Grid>
                        <Grid item component={RightSidebar}/>
                    </Grid>
                </Switch>
            </div>
        </Router>
      );
}

export default App;
