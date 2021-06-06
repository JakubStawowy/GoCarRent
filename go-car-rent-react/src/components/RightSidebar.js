import React from 'react';
import {AppBar, Typography, Button, Grid, Avatar} from "@material-ui/core";
import {NavLink} from "react-router-dom";

import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import PersonIcon from '@material-ui/icons/Person';
import MessageIcon from '@material-ui/icons/Message';
import SettingsIcon from '@material-ui/icons/Settings';

import userImage from '../uploads/user.png';
import {useRightSideBarStyles} from "../style/RightSideBarStyles";
import {useSideBarStyles} from "../style/SideBarStyles";
import {useHistory} from "react-router";

export default function RightSidebar(props) {

    const classes = useRightSideBarStyles();
    const sideBarClasses = useSideBarStyles();
    const history = useHistory();

    const handleNavLinkClick = (e) => {
        props.action();
        if (localStorage.getItem('token') === null) {
            e.preventDefault();
            alert("You don't have access to resources. You must be logged in");
        }
    }

    return (
        <Grid item xs className={props.sideBarStatus && sideBarClasses.sideBar }>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/users/' + props.action1() + '/profile'} onClick={(e)=>handleNavLinkClick(e)}>
                    <div className={classes.circlePlate}>
                        <Avatar
                            src={userImage}
                            alt={''}
                            className={classes.circle}
                        />
                    </div>
                </NavLink>
                <Button className={classes.button} onClick={(e)=> {
                    history.replace('/users/' + localStorage.getItem('userId') + '/profile');
                    handleNavLinkClick(e)
                }}>
                    <Typography variant={"BUTTON"} size={'large'} className={classes.label}>profile</Typography>
                    <PersonIcon />
                </Button>
                <NavLink className={classes.navLink} to={"/users/messages"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>messages</Typography>
                        <MessageIcon />
                    </Button>
                </NavLink>
                <NavLink className={classes.navLink} to={"/users/{id}/cars"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>your cars</Typography>
                        <DirectionsCarIcon />
                    </Button>
                </NavLink>
                <NavLink className={classes.navLink} to={"/settings"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>settings</Typography>
                        <SettingsIcon />
                    </Button>
                </NavLink>
            </AppBar>
        </Grid>
    );
};