import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {Button, Container, Fab, Grid, List, ListItem, makeStyles, Typography} from "@material-ui/core";
import Advertisement from "../components/Advertisement";
import SearchIcon from '@material-ui/icons/Search';
import TuneIcon from '@material-ui/icons/Tune';
import cars from '../data/cars';
import {useSelector} from "react-redux";
import {useHistory} from "react-router";
import {getAnnouncements} from "../actions/getAnnouncements";

const useStyles = makeStyles((theme) => ({
    list: {
        maxHeight: '72vh',
        marginTop: '2em',
        overflow: 'auto'
    },
    container: {marginTop: '2em'},
    buttonContainer: {
        display: "flex",
        justifyContent: "space-around",
    },
    button: {
        background: '#4BBEBAE0',
        width: '40%',
    }
}));

export default function Home() {
    const classes = useStyles();

    const [announcements, setAnnouncements] = useState([]);
    useEffect(() => {
       getAnnouncements().then((response) => {
           setAnnouncements(response.data);
       })
    });

    return (
            <Container className={classes.container}>
                <Container className={classes.buttonContainer}>
                    <Fab variant={'extended'} className={classes.button}>
                        <SearchIcon fontSize={"large"} htmlColor={'white'} />
                    </Fab>
                    <Fab variant={'extended'} className={classes.button}>
                        <TuneIcon fontSize={"large"} htmlColor={'white'}/>
                    </Fab>
                </Container>
                <List className={classes.list}>
                    {announcements.map(announcement => {
                        return (
                            <ListItem>
                                <Advertisement
                                    title={announcement.announcementDetails.title}
                                    date={announcement.createdAt}
                                    price={announcement.announcementDetails.amount}
                                    currency={announcement.announcementDetails.currency}
                                    timeUnit={announcement.announcementDetails.timeUnit}
                                />
                            </ListItem>
                        );
                    })}
                </List>
            </Container>
    );
}