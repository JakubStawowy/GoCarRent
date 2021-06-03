
import LeftSidebar from "./components/LeftSidebar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./views/Home";
import RightSidebar from "./components/RightSidebar";
import FilteringPanel from "./components/FilteringPanel";
import React, {useState} from "react";
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
import Announcement from "./views/Announcement";
import {useSelector} from "react-redux";
import Footer from "./components/Footer";
import {useAppStyles} from "./style/AppStyle";

function App() {

    const classes = useAppStyles();
    const loggedSelector = useSelector((state) => state.isLogged);
    const [sideBarOpened, setSideBarOpened] = useState(true);
    const changeSideBarState = () => setSideBarOpened(!sideBarOpened);
    return (
        <Router>
            <img src={logo} alt={''} className={classes.logo}/>
            <div className={"container"}>
                <Switch>
                    <Grid container style={{height: '100vh'}} wrap={"nowrap"}>
                        <Grid item component={LeftSidebar}
                              sideBarStatus={sideBarOpened}
                              action={changeSideBarState}
                        />
                        <Grid container xs={8} component={List} className={classes.main} wrap={'nowrap'}>
                            <Grid item className={classes.subContainer}>
                                <Route exact path={'/'}>
                                    {loggedSelector.logged ? <Redirect to={'/home'}/> : <Redirect to={'/login'}/>}
                                </Route>
                                <Route path={'/home'} component={Home}/>
                                <Route path={'/announcement/:id'} component={Announcement}/>
                                <Route path={'/announcement/:id/edit'} component={EditAnnouncement}/>
                                <Route path={'/announcement/filter'} component={FilteringPanel}/>
                                <Route path={'/add'} component={AddAnnouncement}/>
                                <Route path={'/user/{id}/rented'} component={RentedCars}/>
                                <Route path={'/login'} component={LoginRegister}/>
                                <Route path={'/users/:id/profile'} component={Profile}/>
                                <Route path={'/users/messages'} component={Messages}/>
                                <Route path={'/users/{id}/cars'} component={UserCars}/>
                                <Route path={'/settings'} component={Settings}/>
                            </Grid>
                        </Grid>
                        <Grid item component={RightSidebar}
                              sideBarStatus={sideBarOpened}
                              action={changeSideBarState}
                        />
                        <Grid item component={Footer} action={changeSideBarState}/>
                    </Grid>
                </Switch>
            </div>
        </Router>
      );
}

export default App;
