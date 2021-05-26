import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {Container, Fab, List, ListItem, makeStyles, TextField} from "@material-ui/core";
import Announcement from "../components/Announcement";
import SearchIcon from '@material-ui/icons/Search';
import TuneIcon from '@material-ui/icons/Tune';
import {getAnnouncements} from "../actions/getAnnouncements";
import {useHistory} from "react-router";
import FilteringPanel from "../components/FilteringPanel";

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
    const [filtersOpen, setFiltersOpen] = useState(false);
    const [searchOpen, setSearchOpen] = useState(false);

    useEffect(() => {
       getAnnouncements().then((response) => {
           setAnnouncements(response.data);
       }).catch(() => {
          localStorage.clear();
          history.replace("/login");

       });
    }, []);

    const changeFiltersOpen = () => setFiltersOpen(!filtersOpen);
    const changeSearchOpen = () => setSearchOpen(!searchOpen);

    const handleSearch = (searchValue) => {
        let body = [];
        searchValue !== undefined && searchValue !== '' &&
            body.push({
                key: "title",
                operation: "=",
                value: searchValue
            });

        getAnnouncements(body).then((response) =>
            setAnnouncements(response.data)
        ).catch((error) => {
            alert(error);
            if(error.response === undefined) {
                localStorage.clear();
                history.replace("/login");
            }
        });
    }

    return (
        <Container className={classes.container}>
            <List className={classes.list}>
                {
                    !filtersOpen && !searchOpen &&
                        <Container className={classes.buttonContainer}>
                            <Fab variant={'extended'} className={classes.button} onClick={changeSearchOpen}>
                                <SearchIcon fontSize={"large"} htmlColor={'white'} />
                            </Fab>
                            <Fab variant={'extended'} className={classes.button} onClick={changeFiltersOpen}>
                                <TuneIcon fontSize={"large"} htmlColor={'white'}/>
                            </Fab>
                        </Container>
                }
                {
                    filtersOpen &&
                    <FilteringPanel action1={changeFiltersOpen} action2={setAnnouncements} />
                }
                {
                    searchOpen &&
                        <Container>
                            <TextField
                                type={'text'}
                                label={'Search'}
                                // value={searchValue}
                                onChange={(e) =>
                                    handleSearch(e.target.value)
                                }
                            />
                            <Fab variant={'extended'} onClick={() => handleSearch()}>Search</Fab>
                            <Fab variant={'extended'} onClick={() => changeSearchOpen()}>Cancel</Fab>
                        </Container>
                }
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
                                status={announcement.status}
                            />
                        </ListItem>
                    );
                })}
            </List>
        </Container>
    );
}