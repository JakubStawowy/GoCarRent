import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {Container, Fab, List, ListItem, makeStyles} from "@material-ui/core";
import Announcement from "../components/Announcement";
import SearchIcon from '@material-ui/icons/Search';
import TuneIcon from '@material-ui/icons/Tune';
import {getAnnouncements} from "../actions/getAnnouncements";
import {useHistory} from "react-router";
import {ERROR_FORBIDDEN} from "../data/errors";

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
    const history = useHistory();

    const [announcements, setAnnouncements] = useState([]);
    useEffect(() => {
       getAnnouncements().then((response) => {
           console.log("Test");
           setAnnouncements(response.data);
       }).catch((error) => {
          if(error.response.status === ERROR_FORBIDDEN) {
              localStorage.clear();
              history.replace("/login");
          }
       });
    }, []);

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
                                <Announcement
                                    announcementId={announcement.id}
                                    title={announcement.title}
                                    date={announcement.createdAt}
                                    price={announcement.amount}
                                    currency={announcement.currency}
                                    timeUnit={announcement.timeUnit}
                                    authorId={announcement.authorId}
                                />
                            </ListItem>
                        );
                    })}
                </List>
            </Container>
    );
}