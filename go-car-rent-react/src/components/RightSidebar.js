import React from 'react';
import './components.css';
import {AppBar, makeStyles, Typography, Button, Grid, Avatar} from "@material-ui/core";
import {NavLink} from "react-router-dom";

import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import PersonIcon from '@material-ui/icons/Person';
import MessageIcon from '@material-ui/icons/Message';
import SettingsIcon from '@material-ui/icons/Settings';

import userImage from '../uploads/user.png';

const useStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '100%',
        position: 'relative',
        justifyContent: 'space-around',
        boxShadow: 'none',
        paddingRight: '3em',

        [theme.breakpoints.down('xs')]: {
            // display: 'none'
            height: '88vh',
            position: 'fixed',
            right: 0,
            width: '50%',
            padding: '0 1em 0 1em'
        }
    },
    button: {
        background: 'white',
        width: '100%',
        borderRadius: '0 30px 30px 0',
        color: '#4BBEBAE0',
        '&:hover': {
            color: 'white'
        },

        [theme.breakpoints.down('xs')]: {
            borderRadius: '30px'
        }
    },
    circlePlate: {
        display: "flex",
        justifyContent: "flex-end"
    },
    circle: {
        width: '10vw',
        height: '10vw',
    },
    img: {
        borderRadius: '50%',
        width: '100%',
        objectFit: 'cover'
    },
    label: {
        [theme.breakpoints.down('sm')]: {
            display: 'none'
        }
    },
    root: {
        [theme.breakpoints.down('xs')]: {
            // display: 'none'
        }
    }
}));

export default function RightSidebar() {
    const classes = useStyles();

    const handleNavLinkClick = (e) => {
        localStorage.getItem('token') === null &&
        e.preventDefault();
    }

    return (
        <Grid item xs className={classes.root}>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/users/' + localStorage.getItem('userId') + '/profile'} onClick={(e)=>handleNavLinkClick(e)}>
                    <div className={classes.circlePlate}>
                        <Avatar
                            src={userImage}
                            alt={''}
                            className={classes.circle}
                        />
                    </div>
                </NavLink>
                <NavLink to={'/users/' + localStorage.getItem('userId') + '/profile'} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button}>
                        <Typography variant={"BUTTON"} size={'large'} className={classes.label}>profile</Typography>
                        <PersonIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/users/{id}/messages"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>messages</Typography>
                        <MessageIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/users/{id}/cars"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>your cars</Typography>
                        <DirectionsCarIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/settings"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"} className={classes.label}>settings</Typography>
                        <SettingsIcon />
                    </Button>
                </NavLink>
            </AppBar>
        </Grid>
    );
};