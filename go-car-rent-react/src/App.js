import './App.css';

import LeftSidebar from "./components/LeftSidebar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./views/Home";
import RightSidebar from "./components/RightSidebar";
import React from "react";
import {Button, Grid, makeStyles} from "@material-ui/core";
import Add from "./views/Add";
import RentedCars from "./views/RentedCars";
import Profile from "./views/Profile";
import UserCars from "./views/UserCars";
import Settings from "./views/Settings";
import LoginRegister from "./views/LoginRegister";
import Messages from "./views/Messages";
import {Redirect} from "react-router";
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';

const useStyles = makeStyles((theme) => ({
    main: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
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
    }
}));
function App() {
    const classes = useStyles();
  return (
    <Router>
        <div className={"container"}>
            <Switch>
                <Grid container style={{height: '100vh'}}>
                    <Grid item component={LeftSidebar} />
                    <Grid container xs={8} className={classes.main}>
                        <Grid item className={classes.subContainer}>
                            <Route exact path={'/'}>
                                <Redirect to={'/home'} />
                            </Route>
                            <Route path={'/home'} component={Home}/>
                            <Route path={'/add'} component={Add}/>
                            <Route path={'/cars/rented'} component={RentedCars}/>
                            <Route path={'/login'} component={LoginRegister}/>
                            <Route path={'/user/profile'} component={Profile}/>
                            <Route path={'/messages'} component={Messages}/>
                            <Route path={'/user/cars'} component={UserCars}/>
                            <Route path={'/settings'} component={Settings}/>
                        </Grid>
                        <Button className={classes.footer}>
                            Footer
                            <KeyboardArrowUpIcon/>
                        </Button>
                    </Grid>
                    <Grid item component={RightSidebar} />
                </Grid>
            </Switch>
        </div>
    </Router>
  );
}

export default App;
