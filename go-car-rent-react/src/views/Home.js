import React, {useEffect, useState} from 'react';
import {Container, Fab, List, ListItem, TextField, Typography} from "@material-ui/core";
import AnnouncementListItem from "../components/AnnouncementListItem";
import SearchIcon from '@material-ui/icons/Search';
import TuneIcon from '@material-ui/icons/Tune';
import {getAnnouncements, getUserAnnouncements} from "../actions/actionRepository";
import {useHistory} from "react-router";
import FilteringPanel from "../components/FilteringPanel";
import ClearIcon from "@material-ui/icons/Clear";
import {useHomeStyles} from "../style/HomeStyles";

export default function Home(props) {
    const classes = useHomeStyles();
    const history = useHistory();

    const [announcements, setAnnouncements] = useState([]);
    const [filtersOpen, setFiltersOpen] = useState(false);
    const [searchOpen, setSearchOpen] = useState(false);

    useEffect(() => {
        props.match.params.id === undefined ?
       getAnnouncements().then((response) => {
           setAnnouncements(response.data);
       }).catch(() => {
          localStorage.clear();
          history.replace("/login");
       })
            :
            getUserAnnouncements(props.match.params.id).then((response) => {
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
                    props.match.params.id !== undefined ?
                        <Typography  variant={'h4'} align={'center'}>
                            User announcements
                        </Typography> :
                        <div>
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
                                <Container className={classes.searchingPanel}>
                                    <div className={classes.searchField}>
                                        <TextField
                                            type={'text'}
                                            label={'Search'}
                                            className={classes.textField}
                                            onChange={(e) =>
                                                handleSearch(e.target.value)
                                            }
                                        />
                                    </div>
                                    <div>
                                        <Fab variant={'extended'} onClick={() => handleSearch()}>
                                            search
                                            <SearchIcon fontSize={"large"}/>
                                        </Fab>
                                        <Fab variant={'extended'} onClick={() => changeSearchOpen()}>
                                            cancel
                                            <ClearIcon fontSize={"large"}/>
                                        </Fab>
                                    </div>
                                </Container>
                            }
                        </div>
                }

                {
                    announcements.length > 0 ?
                    announcements.map(announcement => {
                    return (
                        <ListItem>
                            <AnnouncementListItem
                                announcementId={announcement.id}
                                title={announcement.title}
                                createdAt={announcement.createdAt}
                                price={announcement.amount}
                                currency={announcement.currency}
                                timeUnit={announcement.timeUnit}
                                authorId={announcement.authorId}
                                status={announcement.status}
                            />
                        </ListItem>
                    );
                })
                :
                        <Typography variant={'h5'} align={'center'}>
                            There's no announcements yet
                        </Typography>
                }
            </List>
        </Container>
    );
}